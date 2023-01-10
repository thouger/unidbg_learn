precision highp float;
varying vec2 textureCoordinate;
varying vec2 sucaiTextureCoordinate;
//varying float varyingOpacity;

uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;

uniform float intensity;

#ifndef BLEND_SOFTLIGHT
#define BLEND_SOFTLIGHT

float blendSoftLight(float base, float blend) {
    return (blend < 0.5) ? (2.0 * base * blend + base * base * (1.0 - 2.0 * blend)) : (sqrt(base) * (2.0 * blend - 1.0) + 2.0 * base * (1.0 - blend));
}

vec3 blendSoftLight(vec3 base, vec3 blend) {
    return vec3(blendSoftLight(base.r, blend.r), blendSoftLight(base.g, blend.g), blendSoftLight(base.b, blend.b));
}

vec3 blendSoftLight(vec3 base, vec3 blend, float opacity) {
    return (blendSoftLight(base, blend) * opacity + base * (1.0 - opacity));
}

#endif

#define blendModel blendSoftLight


void main(void)
{
    vec4 src = texture2D(inputImageTexture, textureCoordinate);
    vec4 sucai = texture2D(inputImageTexture2, sucaiTextureCoordinate);

    vec3 color = blendModel(src.rgb, clamp(sucai.rgb * (1.0 / sucai.a), 0.0, 1.0));
    color = mix(src.rgb, color, sucai.a);
    color = mix(src.rgb, color, intensity);
    gl_FragColor = vec4(color, 1.0);
}
