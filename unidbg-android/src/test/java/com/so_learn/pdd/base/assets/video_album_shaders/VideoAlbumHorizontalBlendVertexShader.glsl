attribute vec4 a_Position;
attribute vec2 a_ImageTextureCoordinates;
attribute vec2 a_VideoTextureCoordinates;

varying vec2 v_ImageTextureCoordinates;
varying vec2 v_VideoTextureCoordinates;

void main() {
    v_ImageTextureCoordinates = a_ImageTextureCoordinates;
    v_VideoTextureCoordinates = a_VideoTextureCoordinates;
    gl_Position = a_Position;
}