precision mediump float;

varying highp vec2 textureCoordinate;
uniform sampler2D inputImageTexture;

uniform int hasFace;

void main()
{
    //检测到人脸
    if (hasFace == 1) {
        gl_FragColor = vec4(0.0f, 1.0f, 0.0f, 1.0f);
    }
    //未检测到人脸
    else {
        gl_FragColor = texture2D(inputImageTexture, textureCoordinate);
    }


}

