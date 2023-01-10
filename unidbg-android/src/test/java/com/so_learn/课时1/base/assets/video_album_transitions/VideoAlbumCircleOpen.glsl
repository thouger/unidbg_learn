// author: gre
// License: MIT
uniform float smoothness; // = 0.3
uniform bool opening; // = true

const vec2 center = vec2(0.5, 0.5);
const float SQRT_2 = 1.414213562373;

vec4 transition (vec2 uv) {
  float x = opening ? progress : 1.-progress;
  float m = smoothstep(-smoothness, 0.0, SQRT_2*distance(center, uv) - x*(1.+smoothness));
  return mix(getFromColor(uv), getToColor(uv), opening ? 1.-m : m);
}

vec4 transition (vec2 uv,vec2 uv2) {
    vec2 ratio2 = vec2(1.0, 1.0 / ratio);
    float s = pow(2.0 * abs(progress - 0.5), 3.0);
    float dist = length((vec2(uv) - 0.5) * ratio2);
    float m = smoothstep(-smoothness, 0.0, SQRT_2*dist - progress*(1.+smoothness));
    return mix(getFromColor(uv), getToColor(uv2), 1.-m);
}
