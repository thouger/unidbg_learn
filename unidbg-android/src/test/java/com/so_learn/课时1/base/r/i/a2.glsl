uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;
varying mediump vec2 textureCoordinate;
void main() {
    lowp vec3 iColor = texture2D(inputImageTexture, textureCoordinate).rgb;
    lowp vec3 meanColor = texture2D(inputImageTexture2, textureCoordinate).rgb;
    lowp vec3 diffColor = (iColor - meanColor) * 7.07;
    diffColor = min(diffColor * diffColor, 1.0);
    gl_FragColor = vec4(diffColor, 1.0);
}
