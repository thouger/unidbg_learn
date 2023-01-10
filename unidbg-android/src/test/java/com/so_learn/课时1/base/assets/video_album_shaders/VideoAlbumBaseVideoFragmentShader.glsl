#extension GL_OES_EGL_image_external : require
precision mediump float;
uniform samplerExternalOES u_InputTexture;
varying vec2 v_TextureCoordinates;
void main () {
    gl_FragColor = texture2D(u_InputTexture, v_TextureCoordinates);
}
