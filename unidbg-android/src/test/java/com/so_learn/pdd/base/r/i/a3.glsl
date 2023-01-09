precision highp float;

varying vec2 textureCoordinate;
varying vec2 sucaiTextureCoordinate;

uniform sampler2D inputImageTexture; //原图
uniform sampler2D inputImageTexture2;//

uniform float intensity;
uniform float colorRGB[3];
uniform float inputColor;
// uniform float blendMode;

vec3 blendMultiply(vec3 base, vec3 blend) {
    return base * blend;
}

void main()
{
    vec4 src = texture2D(inputImageTexture, textureCoordinate);
    vec4 sucai = texture2D(inputImageTexture2, sucaiTextureCoordinate);
    float alpha = sucai.a;

    vec3 color = src.rgb;
    if(alpha > 0.0001){
        color = blendMultiply(src.rgb, clamp(sucai.rgb/alpha, 0.0, 1.0));
    }
    gl_FragColor = vec4(mix(src.rgb, color, alpha * intensity), 1.0);
}
