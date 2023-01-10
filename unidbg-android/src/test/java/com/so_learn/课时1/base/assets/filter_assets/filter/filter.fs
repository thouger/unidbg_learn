#ifdef GL_ES
#ifdef GL_FRAGMENT_PRECISION_HIGH
precision highp float;
#else
precision mediump float;
#endif
#endif

varying vec2 textureCoordinate;

uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;

uniform vec2 u_resolution;
uniform float u_time;
uniform float intensity;

vec4 clut(vec4 textureColor, sampler2D textureLut) {
    float blueColor = textureColor.b * 63.0;
    
    vec2 quad1;
    quad1.y = floor(floor(blueColor) / 8.0);
    quad1.x = floor(blueColor) - (quad1.y * 8.0);
    
    vec2 quad2;
    quad2.y = floor(ceil(blueColor) / 8.0);
    quad2.x = ceil(blueColor) - (quad2.y * 8.0);
    
    vec2 texPos1;
    texPos1.x = (quad1.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);
    texPos1.y = (quad1.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);
    
    vec2 texPos2;
    texPos2.x = (quad2.x * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.r);
    texPos2.y = (quad2.y * 0.125) + 0.5/512.0 + ((0.125 - 1.0/512.0) * textureColor.g);
    
    vec4 newColor1 = texture2D(textureLut, texPos1);
    vec4 newColor2 = texture2D(textureLut, texPos2);
    
    vec4 newColor = mix(newColor1, newColor2, fract(blueColor));
    return mix(textureColor, vec4(newColor.rgb, textureColor.w), intensity);
}

void main() {
    vec2 uv = textureCoordinate;
    
    vec4 srcColor = texture2D(inputImageTexture, fract(uv));
    gl_FragColor = clut(srcColor,inputImageTexture2);
}

