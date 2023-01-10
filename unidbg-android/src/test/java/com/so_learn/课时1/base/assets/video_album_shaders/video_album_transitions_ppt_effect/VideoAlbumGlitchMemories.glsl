
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

vec4 transition(vec2 p) {
  vec2 block = floor(p.xy / vec2(16));
  vec2 uv_noise = block / vec2(64);
  uv_noise += floor(vec2(progress) * vec2(1200.0, 3500.0)) / vec2(64);
  vec2 dist = progress > 0.0 ? (fract(uv_noise) - 0.5) * 0.3 *(1.0 -progress) : vec2(0.0);
  vec2 red = p + dist * 0.2;
  vec2 green = p + dist * .3;
  vec2 blue = p + dist * .5;

  return vec4(mix(getFromColor(red), getToColor(red), progress).r,mix(getFromColor(green), getToColor(green), progress).g,mix(getFromColor(blue), getToColor(blue), progress).b,1.0);
}