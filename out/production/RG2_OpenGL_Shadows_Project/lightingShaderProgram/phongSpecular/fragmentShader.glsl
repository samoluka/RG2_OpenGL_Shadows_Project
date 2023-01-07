#version 430

in vec3 fragmentColor;
in vec3 fragmentNormal;
in vec3 lightPositionInCameraSpace;
in vec3 vertexPositionInCameraSpace;

uniform vec3 specularColor;
uniform float specularPower;
uniform vec2 coefficients;

out vec4 color;

void main ( ) {
	vec3 viewVector  = normalize ( -vertexPositionInCameraSpace );
	vec3 lightVector = normalize ( lightPositionInCameraSpace - vertexPositionInCameraSpace );
	vec3 normal      = normalize ( fragmentNormal );
	vec3 reflected   = reflect ( -lightVector, normal );
	float diffuse    = max ( dot ( normal, lightVector ), 0);
	float specular   = pow ( max ( dot ( viewVector, reflected ), 0 ), specularPower );

	color = coefficients.x * vec4 ( diffuse * fragmentColor, 1.0 ) + coefficients.y * vec4 ( specularColor, 1.0 ) * specular;
}
