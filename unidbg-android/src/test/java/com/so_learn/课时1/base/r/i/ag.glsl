precision highp float;
varying vec2 textureCoordinate;
uniform sampler2D inputImageTexture;
uniform float scale;
uniform vec4 backColor;

void main() {
    float width = 1.0 * scale;
    float height = 1.0 * scale;

    float topX = (1.0 - width) * 0.5;
    float topY = (1.0 - height) * 0.5;

    float x = topX + textureCoordinate.x * width;
    float y = topY + textureCoordinate.y * height;

    if(x>1.0||y>1.0||x<0.0||y<0.0){
        gl_FragColor = backColor;
    }else{
        gl_FragColor = texture2D(inputImageTexture, vec2(x, y));
    }


}
