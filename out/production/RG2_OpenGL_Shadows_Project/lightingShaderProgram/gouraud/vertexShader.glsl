#version 430

layout ( location = 0 ) in vec3 vertex;
layout ( location = 1 ) in vec3 color;
layout ( location = 2 ) in vec3 normal;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 model;
uniform mat3 normalTransform;
uniform vec3 lightPosition;

out vec4 fragmentColor;

void main ( ) {
	vec3 normalInCameraSpace = normalize ( normalTransform * normal );

	vec4 lightPositionInCameraSpace  = view * vec4 ( lightPosition, 1 );
	vec4 vertexPositionInCameraSpace = view * model * vec4 ( vertex, 1.0 );

	vec3 lightVector = normalize ( vec3 ( lightPositionInCameraSpace.xyz - vertexPositionInCameraSpace.xyz ) );

	float diffuse = max ( dot ( normalInCameraSpace, lightVector ), 0 );

	fragmentColor = vec4 ( diffuse * color, 1 );
	gl_Position   = projection * view * model * vec4 ( vertex, 1.0 );
}
