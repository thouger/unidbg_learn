precision mediump float;
varying highp vec2 textureCoordinate;

varying highp vec2 texEpmShift1;
varying highp vec2 texEpmShift2;
varying highp vec2 texEpmShift3;
varying highp vec2 texEpmShift4;

uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;
uniform sampler2D inputImageTexture3;
uniform sampler2D inputImageTexture4;

uniform lowp float blurAlpha;
uniform highp float sharpen;
uniform mediump float noise;
uniform mediump float epm;
uniform lowp float whitenIntensity;
uniform lowp float isWhitenLutReady;

uniform lowp float useMask;
uniform lowp float videoReductionNoise;

const float theta = 0.1;


lowp vec3 lookupTable(sampler2D lutTex,lowp vec3 textureColor) {
    highp float blueColor = textureColor.b * 63.0;

    highp vec2 quad1;
    quad1.y = floor(floor(blueColor) / 8.0);
    quad1.x = floor(blueColor) - (quad1.y * 8.0);
    highp vec2 quad2;
    quad2.y = floor(ceil(blueColor) / 8.0);
    quad2.x = ceil(blueColor) - (quad2.y * 8.0);

    highp vec2 texPos1;
    texPos1.x = (quad1.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);
    texPos1.y = (quad1.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);
    texPos1.y = 1.0 - texPos1.y;

    highp vec2 texPos2;
    texPos2.x = (quad2.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);
    texPos2.y = (quad2.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);
    texPos2.y = 1.0 - texPos2.y;

    lowp vec4 newColor2_1 = texture2D(lutTex, texPos1);
    lowp vec4 newColor2_2 = texture2D(lutTex, texPos2);
    lowp vec4 newColor22 = mix(newColor2_1, newColor2_2, fract(blueColor));
    return newColor22.rgb;
}

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
    mediump float epmSum = texture2D(inputImageTexture, texEpmShift1).g;
    epmSum += texture2D(inputImageTexture, texEpmShift2).g;
    epmSum += texture2D(inputImageTexture, texEpmShift3).g;
    epmSum += texture2D(inputImageTexture, texEpmShift4).g;
    epmSum = epmSum * 0.25;


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


    if (isWhitenLutReady >= 0.5) {
        lowp vec3 whitenColor = lookupTable(inputImageTexture4,tmpColor);
        tmpColor = mix(tmpColor, whitenColor, whitenIntensity);
    }
    gl_FragColor = vec4(clamp(tmpColor, 0.0, 1.0), 1.0);
}
