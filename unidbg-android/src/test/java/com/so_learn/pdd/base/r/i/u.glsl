attribute vec3 position;
attribute vec2 inputTextureCoordinate;

varying vec2 textureCoordinate;
varying vec2 sucaiTextureCoordinate;

uniform mat4 modelMatrix;

void main()
{
    vec2 point = 2.0 * position.xy - 1.0;
    gl_Position = vec4(point, 0.0, 1.0);

    textureCoordinate = position.xy;
    sucaiTextureCoordinate = (modelMatrix * vec4(inputTextureCoordinate.xy, 0.0, 1.0)).xy;
}
