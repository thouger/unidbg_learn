attribute vec3 position;
attribute vec2 inputTextureCoordinate;

varying vec2 textureCoordinate;

uniform float texEpmWidthOffset;
uniform float texEpmHeightOffset;

varying vec4 texEpmShift1;
varying vec4 texEpmShift2;
varying vec4 texEpmShift3;
varying vec4 texEpmShift4;

void main()
{
    gl_Position = vec4(position, 1.0);
    textureCoordinate = inputTextureCoordinate;

    texEpmShift1 = vec4(inputTextureCoordinate + vec2(-texEpmWidthOffset, 0.0), inputTextureCoordinate + vec2(texEpmWidthOffset, 0.0));
    texEpmShift2 = vec4(inputTextureCoordinate + vec2(0.0, -texEpmHeightOffset), inputTextureCoordinate + vec2(0.0, texEpmHeightOffset));
    texEpmShift3 = vec4(inputTextureCoordinate + vec2(texEpmWidthOffset, texEpmHeightOffset), inputTextureCoordinate + vec2(-texEpmWidthOffset, -texEpmHeightOffset));
    texEpmShift4 = vec4(inputTextureCoordinate + vec2(-texEpmWidthOffset, texEpmHeightOffset), inputTextureCoordinate + vec2(texEpmWidthOffset, -texEpmHeightOffset));
}
