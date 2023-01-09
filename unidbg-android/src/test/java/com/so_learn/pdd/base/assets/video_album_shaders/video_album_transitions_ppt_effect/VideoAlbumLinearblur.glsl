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

uniform float intensity; // = 0.1
uniform int passes; // = 2

vec4 transition(vec2 uv) {
    vec4 c1 = vec4(0.0);
    vec4 c2 = vec4(0.0);

    float disp = intensity * (0.5 - distance(0.5, progress));
    for (int xi = 0; xi < passes; xi++) {
        float x = float(xi) / float(passes) - 0.5;
        for (int yi = 0; yi < passes; yi++) {
            float y = float(yi) / float(passes) - 0.5;
            vec2 v = vec2(x, y);
            float d = disp;
            c1 += getFromColor(uv + d * v);
            c2 += getToColor(uv + d * v);
        }
    }
    c1 /= float(passes * passes);
    c2 /= float(passes * passes);
    return mix(c1, c2, progress);
}