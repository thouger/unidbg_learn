precision mediump float;

varying vec2 textureCoordinate;
varying vec2 textureCoordinate2;
uniform sampler2D inputImageTexture;
uniform sampler2D inputImageTexture2;
uniform sampler2D inputImageTexture3;

uniform vec2 u_resolution;
uniform float u_time;

uniform int contentMode;///< 0:scaleToFill 1:aspectFit 2:aspectFill
uniform float inputSize[2];
uniform int hasMask;

void main()
{
    vec2 uv = textureCoordinate;
    vec2 uv2 = textureCoordinate2;

    vec4 sourceColor = texture2D(inputImageTexture,uv);
    float maskValue = texture2D(inputImageTexture3, uv).r;
    vec4 backgroundTextureColor = texture2D(inputImageTexture2, uv2);
    if(contentMode == 1 || contentMode == 2){
		vec4 bgColor = vec4(0.0,0.0,0.0,1.0);

		bool outputHeightIsBigger = u_resolution.y >= u_resolution.x;///< 输入的高是否更大
		float inputAspectRatio = inputSize[0] / inputSize[1];///< 输入size比例
		float outputAspectRatio = u_resolution.x / u_resolution.y;///< 输出size比例
		bool isAutomaticHeight = false;///<自适应高

		if(outputHeightIsBigger){
			isAutomaticHeight = inputAspectRatio <= outputAspectRatio ? false : true;
		}else{
			isAutomaticHeight = inputAspectRatio >= outputAspectRatio ? true : false;
		}

		if(contentMode == 2) {
			isAutomaticHeight = !isAutomaticHeight;
		}

		if(isAutomaticHeight){
			float height = inputSize[1] * u_resolution.x / inputSize[0];
			float ndcHeight = height / u_resolution.y;
			float ndcTop = 0.5 - ndcHeight/2.0;
			float ndcBottom = 0.5 + ndcHeight/2.0;

			if(uv2.y >= ndcTop && uv2.y <= ndcBottom){
				backgroundTextureColor = texture2D(inputImageTexture2,vec2(uv2.x,(uv2.y - ndcTop)/ndcHeight));
				if(hasMask == 1){
					gl_FragColor = mix(sourceColor,backgroundTextureColor,maskValue);
				}else{
					gl_FragColor = backgroundTextureColor;
				}

			}else{
				gl_FragColor = bgColor;
			}
		}else{
			float width = inputSize[0] * u_resolution.y / inputSize[1];
			float ndcWidth = width / u_resolution.x;
			float ndcLeft = 0.5 - ndcWidth/2.0;
			float ndcRight = 0.5 + ndcWidth/2.0;

			if(uv2.x >= ndcLeft && uv2.x <= ndcRight){
				backgroundTextureColor = texture2D(inputImageTexture2,vec2((uv2.x - ndcLeft)/ndcWidth, uv2.y));
				if(hasMask == 1){
					gl_FragColor = mix(sourceColor,backgroundTextureColor,maskValue);
				}else{
					gl_FragColor = backgroundTextureColor;
				}
			}else{
				gl_FragColor = bgColor;
			}
		}

	}else{
		if(hasMask == 1){
			gl_FragColor = mix(sourceColor,backgroundTextureColor,maskValue);
		}else{
			gl_FragColor = backgroundTextureColor;
		}
	}
}