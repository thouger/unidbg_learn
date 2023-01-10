precision mediump float;
varying highp vec2 textureCoordinate;

uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;

void main()
{
    lowp vec4 srcColor = texture2D(inputImageTexture, textureCoordinate);
    lowp vec4 makeupColor = texture2D(inputImageTexture2, textureCoordinate);

    gl_FragColor = mix(srcColor, makeupColor, makeupColor.a);
}