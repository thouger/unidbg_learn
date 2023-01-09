// Author: Gaëtan Renaudeau
// License: MIT

/**
 * Transition main fragment shader.
 * author: createchance
 */

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

uniform vec2 direction; // = vec2(1.0, 0.0)

vec4 transition (vec2 uv) {
  vec2 p = uv + progress * sign(direction);
  vec2 f = fract(p);
  return mix(
    getToColor(f),
    getFromColor(f),
    step(0.0, p.y) * step(p.y, 1.0) * step(0.0, p.x) * step(p.x, 1.0)
  );
}
