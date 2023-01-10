precision mediump float;

uniform sampler2D image;
uniform sampler2D video;

varying vec2 imageUV;
varying vec2 videoUV;

void main() {
    vec3 imageColor = texture2D(image, imageUV).rgb;
    vec3 videoColor = texture2D(video, videoUV).rgb;
    vec3 color = 1.0 - (1.0 - imageColor) * (1.0 - videoColor);
    gl_FragColor = vec4(color, 1.0);
}
