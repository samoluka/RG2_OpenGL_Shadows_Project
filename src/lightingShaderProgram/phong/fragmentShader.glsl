#version 430

in vec3 fragmentColor;
in vec3 fragmentNormal;
in vec3 lightPositionInCameraSpace;
in vec3 vertexPositionInCameraSpace;

out vec4 color;

void main ( ) {
	vec3 normal      = normalize ( fragmentNormal );
	vec3 lightVector = normalize ( lightPositionInCameraSpace - vertexPositionInCameraSpace );
	float diffuse    = max ( dot ( normal, lightVector ), 0);

	color = vec4 ( diffuse * fragmentColor, 1.0 );
}
