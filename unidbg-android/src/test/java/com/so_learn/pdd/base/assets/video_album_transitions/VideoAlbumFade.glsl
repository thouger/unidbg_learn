// author: gre
// license: MIT

vec4 transition (vec2 uv) {
  return mix(
    getFromColor(uv),
    getToColor(uv),
    progress
  );
}

vec4 transition (vec2 uv,vec2 uv2) {
  return mix(
    getFromColor(uv),
    getToColor(uv2),
    progress
  );
}
