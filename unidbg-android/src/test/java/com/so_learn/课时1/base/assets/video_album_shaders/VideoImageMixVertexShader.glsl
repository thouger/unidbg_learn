precision mediump float;

attribute vec3 attPosition;
attribute vec2 attUV;

varying vec2 imageUV;
varying vec2 videoUV;

void main() {
    gl_Position = vec4(attPosition.xyz, 1.0);
    imageUV = attUV;
    videoUV = vec2(attUV.x,attUV.y);
}
