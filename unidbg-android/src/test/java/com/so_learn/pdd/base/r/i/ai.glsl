uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;
uniform sampler2D inputImageTexture3;
uniform sampler2D inputImageTexture4;
uniform lowp float smoothIntensity;
uniform lowp float whiteIntensity;
uniform lowp float vividIntensity;
varying lowp vec2 textureCoordinate;
const lowp vec3 rgb2gray = vec3(0.299, 0.587, 0.114);

lowp vec3 lookup8x8(lowp vec3 iColor){
    mediump float blueColor = iColor.b * 63.0;
    
    mediump vec2 quad1;
    quad1.y = floor(floor(blueColor) / 8.0);
    quad1.x = floor(blueColor) - (quad1.y * 8.0);
    
    mediump vec2 texPos1;
    texPos1.x = (quad1.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * iColor.r);
    texPos1.y = (quad1.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * iColor.g);
    
    texPos1.y = 1.0 - texPos1.y;
    
    return texture2D(inputImageTexture4, texPos1).rgb;
}

void main(){
    lowp vec3 iColor = texture2D(inputImageTexture, textureCoordinate).rgb;
    lowp vec3 meanColor = texture2D(inputImageTexture2, textureCoordinate).rgb;
    lowp float rgb_max = max(max(iColor.r, iColor.g), iColor.b);
    lowp float rgb_min = min(min(iColor.r, iColor.g), iColor.b);
    lowp float skinProb = step(0.235, iColor.r)*step(0.157, iColor.g)*step(0.078, iColor.b)*step(0.039, iColor.r-iColor.g)*step(0.039, rgb_max-rgb_min);
    if(skinProb < 0.01){
        iColor += (min(iColor-meanColor, 0.0)+0.015)*vividIntensity;
        iColor = clamp(iColor, 0.0, 1.0);
        gl_FragColor = vec4(mix(iColor, lookup8x8(iColor), whiteIntensity), 1.0);
        return;
    }
    lowp vec3 varColor = texture2D(inputImageTexture3, textureCoordinate).rgb;
    lowp vec3 sigma = (1.0-varColor/(varColor+0.1))*smoothIntensity;
    lowp vec3 smooth = clamp(mix(iColor, meanColor, sigma), 0.0, 1.0);
    //    lowp vec3 curve = clamp(1.39*log(smooth+0.9328)+0.08536, 0.0, 1.0);
    lowp vec3 curve = smooth;
    lowp vec3 ret = mix(curve, smooth, 0.5);
    ret = mix(smooth, ret, 0.7);
    ret += (min(ret-meanColor, 0.0)+0.015)*vividIntensity;
    ret = clamp(ret, 0.0, 1.0);
    gl_FragColor = vec4(mix(ret.rgb, lookup8x8(ret.rgb), whiteIntensity), 1.0);
}


