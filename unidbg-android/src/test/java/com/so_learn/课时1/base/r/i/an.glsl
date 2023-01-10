precision highp float;
varying vec2 textureCoordinate;

uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;

uniform float intensity;
uniform int blendMode;

#ifndef BLEND_ADD
#define BLEND_ADD

float blendAdd(float base, float blend) {
    return min(base + blend, 1.0);
}

vec3 blendAdd(vec3 base, vec3 blend) {
    return min(base + blend, vec3(1.0));
}

#endif


#ifndef BLEND_MULTIPLY
#define BLEND_MULTIPLY

vec3 blendMultiply(vec3 base, vec3 blend) {
    return base * blend;
}

vec3 blendMultiply(vec3 base, vec3 blend, float opacity) {
    return (blendMultiply(base, blend) * opacity + blend * (1.0 - opacity));
}

#endif


#ifndef BLEND_NORMAL
#define BLEND_NORMAL

vec3 blendNormal(vec3 base, vec3 blend) {
    return blend;
}

vec3 blendNormal(vec3 base, vec3 blend, float opacity) {
    return (blendNormal(base, blend) * opacity + blend * (1.0 - opacity));
}
#endif


#ifndef BLEND_SCREEN
#define BLEND_SCREEN

float blendScreen(float base, float blend) {
    return 1.0 - ((1.0 - base) * (1.0 - blend));
}

vec3 blendScreen(vec3 base, vec3 blend) {
    return vec3(blendScreen(base.r, blend.r), blendScreen(base.g, blend.g), blendScreen(base.b, blend.b));
}

vec3 blendScreen(vec3 base, vec3 blend, float opacity) {
    return (blendScreen(base, blend) * opacity + blend * (1.0 - opacity));
}

#endif


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

vec3 processBlend(int blendMode, vec3 base, vec3 blend) {
    if (blendMode == 0) {
        return blendAdd(base, blend);
    } else if (blendMode == 1) {
        return blendMultiply(base, blend);
    } else if (blendMode == 2) {
        return blendNormal(base, blend);
    } else if (blendMode == 3) {
        return blendScreen(base, blend);
    } else if (blendMode == 4) {
        return blendSoftLight(base, blend);
    }
}

void main(void)
{
    vec4 src = texture2D(inputImageTexture, textureCoordinate);
    vec4 sucai = texture2D(inputImageTexture2, textureCoordinate);
    vec3 color = processBlend(blendMode, src.rgb, clamp(sucai.rgb * (1.0 / sucai.a), 0.0, 1.0));
    color = mix(src.rgb, color, sucai.a);
    color = mix(src.rgb, color, intensity);
    gl_FragColor = vec4(color, 1.0);
}