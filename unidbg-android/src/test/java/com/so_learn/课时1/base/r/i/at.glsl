precision highp float;
uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;
varying lowp vec2 textureCoordinate;
uniform float intensity;

void main()
{
    highp vec4 textureColor1 = texture2D(inputImageTexture, textureCoordinate);
    textureColor1 = clamp(textureColor1, 0.0, 1.0);
    
    highp float blueColor = textureColor1.b * 63.0;
    
    highp vec2 quad1;
    quad1.y = floor(floor(blueColor) / 8.0);
    quad1.x = floor(blueColor) - (quad1.y * 8.0);
    highp vec2 quad2;
    quad2.y = floor(ceil(blueColor) / 8.0);
    quad2.x = ceil(blueColor) - (quad2.y * 8.0);
    
    highp vec2 texPos1;
    texPos1.x = (quad1.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor1.r);
    texPos1.y = (quad1.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor1.g);
    texPos1.y = 1.0 - texPos1.y;
    
    highp vec2 texPos2;
    texPos2.x = (quad2.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor1.r);
    texPos2.y = (quad2.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor1.g);
    texPos2.y = 1.0 - texPos2.y;
    
    lowp vec4 newColor2_1 = texture2D(inputImageTexture2, texPos1);
    lowp vec4 newColor2_2 = texture2D(inputImageTexture2, texPos2);
    lowp vec4 newColor22 = mix(newColor2_1, newColor2_2, fract(blueColor));
    gl_FragColor = mix(textureColor1, vec4(newColor22.rgb, textureColor1.w), intensity);
}



