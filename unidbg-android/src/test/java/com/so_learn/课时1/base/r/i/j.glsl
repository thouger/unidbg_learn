uniform sampler2D inputImageTexture;
varying lowp vec2 blurCoordinates[5];
varying lowp float blurWeight[5];
void main(void){
    mediump vec3 sum = vec3(0.0);
    for(int i = 0;i < 5;i++){
        sum += texture2D(inputImageTexture, blurCoordinates[i]).rgb * blurWeight[i];
    }
    gl_FragColor = vec4(sum, 1.0);
}
