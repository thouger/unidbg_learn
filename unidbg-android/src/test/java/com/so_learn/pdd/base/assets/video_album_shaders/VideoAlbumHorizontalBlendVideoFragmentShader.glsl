#extension GL_OES_EGL_image_external : require
precision highp float;

varying highp vec2 v_ImageTextureCoordinates;
varying highp vec2 v_VideoTextureCoordinates;

uniform sampler2D u_InputImageTexture;
uniform samplerExternalOES u_InputVideoTexture;

void main()
{
    vec2 effectuv = vec2(v_VideoTextureCoordinates.x/2.0, v_VideoTextureCoordinates.y);
    vec2 alphauv = vec2(v_VideoTextureCoordinates.x/2.0 + 0.5, v_VideoTextureCoordinates.y);

    vec4 imageTextureColor = texture2D(u_InputImageTexture, v_ImageTextureCoordinates);
    vec4 videoTextureColor = texture2D(u_InputVideoTexture, effectuv);
    lowp float weight = texture2D(u_InputVideoTexture, alphauv).r;

    gl_FragColor = vec4(mix(imageTextureColor.rgb, videoTextureColor.rgb, weight), 1.0);
}