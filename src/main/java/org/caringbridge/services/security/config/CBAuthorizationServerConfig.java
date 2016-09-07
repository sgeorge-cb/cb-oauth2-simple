package org.caringbridge.services.security.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class CBAuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter{

	@Resource(name="authenticationManager")
	private ProviderManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		tokenStore = tokenStore();
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore)
		.accessTokenConverter(tokenEnhancer());
	}	


	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

		oauthServer.allowFormAuthenticationForClients();
		oauthServer
		.tokenKeyAccess("permitAll()") ///oauth/token_key
		.checkTokenAccess("permitAll()"); ///oauth/check_token
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("hallmark")
		.authorizedGrantTypes("client_credentials", "password", "refresh_token")
		.authorities("ROLE_CLIENT")
		.scopes("read", "write", "trust")
		.resourceIds("cboauth2/secret")
		.secret("secret_password");
	}

	@Bean
	public ApprovalStore approvalStore() throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	@Bean
	public JwtAccessTokenConverter tokenEnhancer(){
		final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey("ThisIsAComplexKey");
		return jwtAccessTokenConverter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}


}
