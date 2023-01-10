#ifdef GL_ES
#ifdef GL_FRAGMENT_PRECISION_HIGH
precision highp float;
#else
precision mediump float;
#endif
#endif

varying vec2 textureCoordinate;
uniform sampler2D inputImageTexture;
uniform vec2 u_resolution;

void main()
{
    vec4 alplaColor = texture2D(inputImageTexture, vec2(textureCoordinate.x / 2.0,textureCoordinate.y));
    vec4 videoColor = texture2D(inputImageTexture, vec2(0.5 + textureCoordinate.x / 2.0,textureCoordinate.y));
     
    gl_FragColor = vec4(videoColor.x,videoColor.y,videoColor.z,alplaColor.x);
}
