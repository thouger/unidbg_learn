precision mediump float;
varying highp vec2 textureCoordinate;

varying highp vec4 texEpmShift1;
varying highp vec4 texEpmShift2;
varying highp vec4 texEpmShift3;
varying highp vec4 texEpmShift4;

uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;
uniform sampler2D inputImageTexture3;

uniform lowp float blurAlpha;
uniform highp float sharpen;
uniform mediump float noise;
uniform mediump float epm;

uniform lowp float useMask;
uniform lowp float videoReductionNoise;

const float theta = 0.1;

void main()
{
    //firstly, smooth
    lowp vec4 preColor = texture2D(inputImageTexture2, textureCoordinate);

    lowp vec4 inColor = texture2D(inputImageTexture, textureCoordinate);
    lowp vec3 meanColor = preColor.rgb;

    mediump float p = clamp((min(inColor.r, meanColor.r-0.1)-0.2)*4.0, 0.0, 1.0);
    mediump float kMin = (1.0 - preColor.a / (preColor.a + theta)) * p * blurAlpha;

    // 降噪
    lowp vec3 mask_rgb = vec3(0.0, 0.0, 0.0);
    lowp float threshold = 0.005;

   // if (useMask > 0.001) {
   //     mask_rgb = texture2D(inputImageTexture3, textureCoordinate).rgb;
   // }

   if (videoReductionNoise > 0.001) {
        lowp float rgb_max = max(max(inColor.r, inColor.g), inColor.b);
        lowp float rgb_min = min(min(inColor.r, inColor.g), inColor.b);

        lowp float skinProb = step(0.235, inColor.r)*step(0.157, inColor.g)*step(0.078, inColor.b)*step(0.039, inColor.r-inColor.g)*step(0.039, rgb_max-rgb_min);
        if (skinProb < 0.01) {
            kMin = 1.0 - (preColor.a / (preColor.a + noise));
        }
    }

    lowp vec3 smoothColor = mix(inColor.rgb, meanColor.rgb, kMin);

    //secondly, sharpen
    mediump float epmSum = 0.25 * inColor.g;
    epmSum += 0.125 * texture2D(inputImageTexture, texEpmShift1.xy).g;
    epmSum += 0.125 * texture2D(inputImageTexture, texEpmShift1.zw).g;
    epmSum += 0.125 * texture2D(inputImageTexture, texEpmShift2.xy).g;
    epmSum += 0.125 * texture2D(inputImageTexture, texEpmShift2.zw).g;
    epmSum += 0.0625 * texture2D(inputImageTexture, texEpmShift3.xy).g;
    epmSum += 0.0625 * texture2D(inputImageTexture, texEpmShift3.zw).g;
    epmSum += 0.0625 * texture2D(inputImageTexture, texEpmShift4.xy).g;
    epmSum += 0.0625 * texture2D(inputImageTexture, texEpmShift4.zw).g;

    float hPass = inColor.g - epmSum;

    if (hPass > 0.0) {
        hPass = hPass * 0.8;
    }

    float mask = step(0.0275, abs(hPass));
    float r = abs(smoothColor.r - inColor.r);

    vec3 tmpColor = smoothColor.rgb + (hPass * sharpen);

   // if (useMask > 0.001) {
   //     if (mask_rgb.r < threshold && mask_rgb.b < threshold ) {
   //         tmpColor = smoothColor.rgb + (epm/(epm + r) * mask * hPass * sharpen);
    //    }
   // }

    gl_FragColor = vec4(clamp(tmpColor, 0.0, 1.0), 1.0);
}
