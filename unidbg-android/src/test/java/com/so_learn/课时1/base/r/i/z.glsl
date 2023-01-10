precision highp float;

attribute vec4 position;
attribute vec4 inputTextureCoordinate;

varying vec2 textureCoordinate;

uniform float pointSize;

void main()
{
    gl_Position = position;
    gl_PointSize = pointSize;
    textureCoordinate = inputTextureCoordinate.xy;
}