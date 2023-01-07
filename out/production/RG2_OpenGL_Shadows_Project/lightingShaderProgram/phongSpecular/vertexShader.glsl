#version 430

layout ( location = 0 ) in vec3 vertex;
layout ( location = 1 ) in vec3 color;
layout ( location = 2 ) in vec3 normal;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 model;
uniform mat3 normalTransform;
uniform vec3 lightPosition;

out vec3 fragmentColor;
out vec3 fragmentNormal;
out vec3 lightPositionInCameraSpace;
out vec3 vertexPositionInCameraSpace;

void main ( ) {
	fragmentNormal = normalTransform * normal;

	lightPositionInCameraSpace  = ( view * vec4 ( lightPosition, 1.0 ) ).xyz;
	vertexPositionInCameraSpace = ( view * model * vec4 ( vertex, 1.0 ) ).xyz;
	
	fragmentColor = color;
	
	gl_Position = projection * view * model * vec4 ( vertex, 1.0 );
}
