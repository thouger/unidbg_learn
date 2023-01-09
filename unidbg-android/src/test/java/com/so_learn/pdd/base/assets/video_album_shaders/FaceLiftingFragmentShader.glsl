precision highp float;

varying highp vec2 texCoordinate;
uniform sampler2D inputImageTexture;

uniform int faceCount;

const int FACE_POINT_SIZE = 212;

uniform highp float facePoints[3*212];
uniform vec2 u_resolution; ///< 720 1280

uniform highp float bigEyeDelta;
uniform highp float thinFaceDelta;

#define MIN_INTENSITY 0.001

 highp vec2 warpPositionToUse1(vec2 currentPoint, vec2 contourPointA,  vec2 contourPointB, float radius, float delta, float aspectRatio)
{
    highp vec2 positionToUse = currentPoint;

    vec2 currentPointToUse = vec2(currentPoint.x, currentPoint.y * aspectRatio + 0.5 - 0.5 * aspectRatio);
    vec2 contourPointAToUse = vec2(contourPointA.x, contourPointA.y * aspectRatio + 0.5 - 0.5 * aspectRatio);
    highp float r = distance(currentPointToUse, contourPointAToUse);

    if(r < radius)
    {
        vec2 dir = normalize(contourPointB - contourPointA);
        float dist = radius * radius - r * r;
        float alpha = dist / (dist + (r-delta) * (r-delta));
        alpha = alpha * alpha;

        positionToUse = positionToUse - alpha * delta * dir;
    }

    return positionToUse;
}


 //脸部调节
 vec2 adjust_thinFace(vec2 coord, float eye_dist, vec2 dir_up, vec2 dir_right, float aspect_ratio, float intensity, int faceIndex)
{
    vec2 positionToUse = coord;
    int arraySize = 3;
    vec2 leftContourPoints[3];
    vec2 rightContourPoints[3];

    float deltaArray[3];

    leftContourPoints[0] = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 4*2], facePoints[faceIndex * FACE_POINT_SIZE + 4*2 + 1]) - dir_right * eye_dist*0.13;
    leftContourPoints[1] = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 9*2], facePoints[faceIndex * FACE_POINT_SIZE + 9*2 + 1]) - dir_right * eye_dist*0.33;
    leftContourPoints[2] = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 13*2], facePoints[faceIndex * FACE_POINT_SIZE + 13*2 + 1])- dir_right * eye_dist*0.33;


    rightContourPoints[0] = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 28*2], facePoints[faceIndex * FACE_POINT_SIZE + 28*2 + 1]) + dir_right * eye_dist*0.13;
    rightContourPoints[1] = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 23*2], facePoints[faceIndex * FACE_POINT_SIZE + 23*2 + 1]) + dir_right * eye_dist*0.33;
    rightContourPoints[2] = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 19*2], facePoints[faceIndex * FACE_POINT_SIZE + 19*2 + 1]) + dir_right * eye_dist*0.33;

    float x = 3.14159 / 30.0;
    float scaleFactor = eye_dist * 2.0;
    float radius = 0.4 * scaleFactor;


    deltaArray[0] = sin(x) * intensity * 0.150 * scaleFactor;
    deltaArray[1] = sin(x*2.0) * intensity * 0.150 * scaleFactor;
    deltaArray[2] = sin(x*2.0) * intensity * 0.150 * scaleFactor;


    for(int i = 0; i < arraySize; i++)
    {
        positionToUse = warpPositionToUse1(positionToUse, leftContourPoints[i], rightContourPoints[i], radius, deltaArray[i], aspect_ratio);
        positionToUse = warpPositionToUse1(positionToUse, rightContourPoints[i], leftContourPoints[i], radius, deltaArray[i], aspect_ratio);
    }

    return positionToUse;
}

vec2 enlargeEye(vec2 textureCoord, vec2 originPosition, float radius, float delta) {

    // 和老版逻辑保持一致
    float dis = distance(vec2(textureCoord.x, textureCoord.y) * u_resolution, vec2(originPosition.x, originPosition.y) * u_resolution);

    if (dis < radius) {
        float weight = dis / radius;
        weight = 1.0 - (weight - 1.0) * (weight - 1.0) * delta;
        textureCoord = originPosition + (textureCoord - originPosition) * weight * weight;
    }

    return textureCoord;
}

// eye
vec2 bigEye(vec2 currentCoordinate, int faceIndex) {

    vec3 eyeIndexs[2];
    eyeIndexs[0] = vec3(74, 66, 70);
    eyeIndexs[1] = vec3(83, 75, 79);

    for (int i = 0; i < 2; i++) {
        int centerPointIndex = int(eyeIndexs[i].x);
        int minPointIndex = int(eyeIndexs[i].y);
        int maxPointIndex = int(eyeIndexs[i].z);

        vec2 centerPoint = vec2(facePoints[faceIndex * FACE_POINT_SIZE + centerPointIndex * 2], facePoints[faceIndex * FACE_POINT_SIZE + centerPointIndex * 2 + 1]);
        vec2 minPoint = vec2(facePoints[faceIndex * FACE_POINT_SIZE + minPointIndex * 2], facePoints[faceIndex * FACE_POINT_SIZE + minPointIndex * 2 + 1]);
        vec2 maxPoint = vec2(facePoints[faceIndex * FACE_POINT_SIZE + maxPointIndex * 2], facePoints[faceIndex * FACE_POINT_SIZE + maxPointIndex * 2 + 1]);

        // 和老版逻辑保持一致
        float radius = distance(vec2(maxPoint.x, maxPoint.y) * u_resolution, vec2(minPoint.x, minPoint.y) * u_resolution) * 1.5;

        currentCoordinate = enlargeEye(currentCoordinate, centerPoint, radius, bigEyeDelta);
    }

    return currentCoordinate;
}

// face
vec2 thinFace(vec2 currentCoordinate, int faceIndex) {
    // 眼距
    vec2 leftEyeCenterPoint = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 74 * 2], facePoints[faceIndex * FACE_POINT_SIZE + 74 * 2 + 1]);
    vec2 rightEyeCenterPoint = vec2(facePoints[faceIndex * FACE_POINT_SIZE + 83 * 2], facePoints[faceIndex * FACE_POINT_SIZE + 83 * 2 + 1]);
    float eye_dist = distance(leftEyeCenterPoint, rightEyeCenterPoint);

    // 屏幕高宽比
    float aspect_ratio = u_resolution.y / u_resolution.x;

    // 面部方向
    vec2 dir_up     = normalize(vec2(facePoints[faceIndex * FACE_POINT_SIZE + 51 * 2], facePoints[faceIndex * FACE_POINT_SIZE + 51 * 2 + 1]) - vec2(facePoints[faceIndex * FACE_POINT_SIZE + 16 * 2], facePoints[faceIndex * FACE_POINT_SIZE + 16 * 2 + 1]));
    vec2 dir_right  = normalize(vec2(facePoints[faceIndex * FACE_POINT_SIZE + 83 * 2], facePoints[faceIndex * FACE_POINT_SIZE + 83 * 2 + 1]) - vec2(facePoints[faceIndex * FACE_POINT_SIZE + 74 * 2], facePoints[faceIndex * FACE_POINT_SIZE + faceIndex * FACE_POINT_SIZE + 74 * 2 + 1]));

    currentCoordinate = adjust_thinFace(currentCoordinate, eye_dist, dir_up, dir_right, aspect_ratio, thinFaceDelta, faceIndex);

    return currentCoordinate;
}

void main(){
    vec2 curCoord = texCoordinate;

    for (int faceIndex = 0; faceIndex < faceCount; faceIndex++) {
//        for (int i = 0; i < 106; i++) {
//            if (abs(curCoord.x - facePoints[faceIndex * FACE_POINT_SIZE + i * 2]) < 0.005 && abs(curCoord.y - facePoints[faceIndex * FACE_POINT_SIZE + i * 2 + 1]) < 0.005) {
//                gl_FragColor = vec4(0.0, 0.0, 1.0, 1.0);
//                return;
//            }
//        }

        if (bigEyeDelta > MIN_INTENSITY) {
            curCoord = bigEye(curCoord, faceIndex);
        }

        if (thinFaceDelta > MIN_INTENSITY) {
            curCoord = thinFace(curCoord, faceIndex);
        }

        gl_FragColor = texture2D(inputImageTexture, curCoord);
    }
}
