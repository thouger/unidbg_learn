attribute vec3 position;
attribute vec2 inputTextureCoordinate;
varying vec2 blurCoordinates[5];
varying float blurWeight[5];
uniform float texelWidthOffset;
uniform float texelHeightOffset;
void main(void){
    gl_Position = vec4(position, 1.0);
    blurCoordinates[0] = inputTextureCoordinate + vec2(-texelWidthOffset*3.3635, -texelHeightOffset*3.3635);
    blurCoordinates[1] = inputTextureCoordinate + vec2(-texelWidthOffset*1.4403, -texelHeightOffset*1.4403);
    blurCoordinates[2] = inputTextureCoordinate;
    blurCoordinates[3] = inputTextureCoordinate + vec2(texelWidthOffset*1.4403, texelHeightOffset*1.4403);
    blurCoordinates[4] = inputTextureCoordinate + vec2(texelWidthOffset*3.3635, texelHeightOffset*3.3635);
    
    blurWeight[0] = 0.1312;
    blurWeight[1] = 0.2830;
    blurWeight[2] = 0.1716;
    blurWeight[3] = 0.2830;
    blurWeight[4] = 0.1312;
}
