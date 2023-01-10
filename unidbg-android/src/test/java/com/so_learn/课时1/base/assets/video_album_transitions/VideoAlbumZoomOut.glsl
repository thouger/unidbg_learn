// Author: 0gust1
// License: MIT
uniform float maxScale; // = 1.2

vec2 zoom(vec2 uv, float scale) {
    float scaleX = 0.5 + (uv.x - 0.5) / scale;
    float scaleY = 0.5 + (uv.y - 0.5) / scale;
    return vec2(scaleX, scaleY);
}

vec4 transition (vec2 uv) {
  return  getFromColor(zoom(uv, sqrt(1.0 + (maxScale - 1.0) * progress)));
}