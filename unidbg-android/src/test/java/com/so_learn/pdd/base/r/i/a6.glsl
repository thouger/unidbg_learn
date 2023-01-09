attribute vec2 position;
attribute vec2 inputTextureCoordinate;
//attribute float attOpacity;

varying vec2 textureCoordinate;
varying vec2 sucaiTextureCoordinate;
//varying float varyingOpacity;

uniform mat4 modelMatrix;

void main(void){
    vec2 point = 2.0 * position.xy - 1.0;
    gl_Position = vec4(point, 0.0, 1.0);

    textureCoordinate = position.xy;
    sucaiTextureCoordinate = (modelMatrix * vec4(inputTextureCoordinate.xy, 0.0, 1.0)).xy;
}
