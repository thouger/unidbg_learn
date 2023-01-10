precision highp float;

uniform sampler2D u_InputTexture;
uniform bool u_Vertical;
uniform mediump float u_SampleWeight[7];
uniform highp float u_WidthStep;
uniform highp float u_HeightStep;
varying vec2 v_TextureCoordinates;

void main()
{
    vec4 orColor = texture2D(u_InputTexture, v_TextureCoordinates);
    float orAlpha = orColor.a;
    vec3 color = orColor.rgb * u_SampleWeight[0];
    if (!u_Vertical){
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x + u_WidthStep, v_TextureCoordinates.y)).rgb * u_SampleWeight[1];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x - u_WidthStep, v_TextureCoordinates.y)).rgb * u_SampleWeight[1];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x + u_WidthStep * 2.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[2];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x - u_WidthStep * 2.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[2];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x + u_WidthStep * 3.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[3];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x - u_WidthStep * 3.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[3];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x + u_WidthStep * 4.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[4];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x - u_WidthStep * 4.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[4];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x + u_WidthStep * 5.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[5];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x - u_WidthStep * 5.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[5];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x + u_WidthStep * 6.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[6];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x - u_WidthStep * 6.0, v_TextureCoordinates.y)).rgb * u_SampleWeight[6];
    } else {
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y + u_HeightStep * 1.0)).rgb * u_SampleWeight[1];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y - u_HeightStep * 1.0)).rgb * u_SampleWeight[1];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y + u_HeightStep * 2.0)).rgb * u_SampleWeight[2];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y - u_HeightStep * 2.0)).rgb * u_SampleWeight[2];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y + u_HeightStep * 3.0)).rgb * u_SampleWeight[3];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y - u_HeightStep * 3.0)).rgb * u_SampleWeight[3];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y + u_HeightStep * 4.0)).rgb * u_SampleWeight[4];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y - u_HeightStep * 4.0)).rgb * u_SampleWeight[4];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y + u_HeightStep * 5.0)).rgb * u_SampleWeight[5];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y - u_HeightStep * 5.0)).rgb * u_SampleWeight[5];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y + u_HeightStep * 6.0)).rgb * u_SampleWeight[6];
        color += texture2D(u_InputTexture, vec2(v_TextureCoordinates.x, v_TextureCoordinates.y - u_HeightStep * 6.0)).rgb * u_SampleWeight[6];
    }
    gl_FragColor = vec4(color, orAlpha);
}