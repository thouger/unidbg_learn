precision mediump float;

uniform sampler2D u_InputTexture;
uniform sampler2D u_InputTexture2;
uniform float progress;
uniform float ratio;
uniform float ratio2;

varying vec2 texCoordinate;

vec4 transition (vec2 p);

void main() {
    gl_FragColor = transition(texCoordinate);
}

vec4 getFromColor(vec2 coordinate) {
    return texture2D(u_InputTexture, vec2(coordinate.x, coordinate.y));
}

vec4 getToColor(vec2 coordinate) {
    vec2 adjustedCoor = 0.5 + (coordinate - 0.5) * vec2(min(ratio / ratio2, 1.0), min(ratio2 / ratio, 1.0));
    vec2 sampleCoor = vec2(adjustedCoor.x, adjustedCoor.y);
    return texture2D(u_InputTexture2, sampleCoor);
}

uniform float zoom_quickness;// = 0.8

vec2 zoom(vec2 uv, float amount) {
    return 0.5 + ((uv - 0.5) * (1.0-amount));
}

vec4 transition (vec2 uv) {
    float nQuick = clamp(zoom_quickness, 0.2, 1.0);
    return mix(
    getFromColor(zoom(uv, smoothstep(0.0, nQuick, progress))),
    getToColor(uv),
    smoothstep(nQuick - 0.2, 1.0, progress)
    );
}