precision highp float;
varying vec2 textureCoordinate;
varying vec2 sucaiTextureCoordinate;
//varying float varyingOpacity;

uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;

uniform float intensity;

#ifndef BLEND_NORMAL
#define BLEND_NORMAL

vec3 blendNormal(vec3 base, vec3 blend) {
    return blend;
}

vec3 blendNormal(vec3 base, vec3 blend, float opacity) {
    return (blendNormal(base, blend) * opacity + blend * (1.0 - opacity));
}
#endif

#define blendModel blendNormal


void main(void)
{
    vec4 src = texture2D(inputImageTexture, textureCoordinate);
    vec4 sucai = texture2D(inputImageTexture2, sucaiTextureCoordinate);

    vec3 color = blendModel(src.rgb, clamp(sucai.rgb * (1.0 / sucai.a), 0.0, 1.0));
    color = mix(src.rgb, color, sucai.a);
    color = mix(src.rgb, color, intensity);
    gl_FragColor = vec4(color, 1.0);
}
