
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

uniform vec4 bgcolor;// = vec4(0.0, 0.0, 0.0, 1.0)

vec4 transition(vec2 p) {
  vec2 ratio2 = vec2(1.0, 1.0 / ratio);
  float s = pow(2.0 * abs(progress - 0.5), 3.0);
  float dist = length((vec2(p) - 0.5) * ratio2);
  return mix(
    progress < 0.5 ? getFromColor(p) : getToColor(p), // branching is ok here as we statically depend on progress uniform (branching won't change over pixels)
    bgcolor,
    step(s, dist)
  );
}