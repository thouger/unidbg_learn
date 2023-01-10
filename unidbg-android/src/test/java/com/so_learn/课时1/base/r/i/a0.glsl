attribute vec4 position;
attribute vec4 inputTextureCoordinate;
varying vec2 textureCoordinate;
uniform float imageWidth;
uniform float imageHeight;
uniform float needNormalize;
void main()
{
    if (needNormalize > 0.5) {
        vec2 resolution = vec2(imageWidth,imageHeight);
        vec2 pos = position.xy/resolution * 2.0 - 1.0;
        gl_Position = vec4(pos,0.0,1.0);
        textureCoordinate = inputTextureCoordinate.xy/resolution;
    } else {
        gl_Position = vec4(position.xy,0.0,1.0);
        textureCoordinate = inputTextureCoordinate.xy;
    }

}
