uniform mat4 uMVPMatrix;        // 变换矩阵
attribute vec4 position;       // 图像顶点坐标
attribute vec4 inputTextureCoordinate;   // 图像纹理坐标

varying vec2 textureCoordinate; // 图像纹理坐标

void main() {
    gl_Position = uMVPMatrix * position;
    textureCoordinate = inputTextureCoordinate.xy;
}