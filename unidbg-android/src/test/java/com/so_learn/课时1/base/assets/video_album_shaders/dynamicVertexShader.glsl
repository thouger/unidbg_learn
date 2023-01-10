attribute vec4 attPosition;
attribute vec2 attUV;

varying vec2 texCoordinate;

void main() {
    texCoordinate = attUV;
    gl_Position = attPosition;
}