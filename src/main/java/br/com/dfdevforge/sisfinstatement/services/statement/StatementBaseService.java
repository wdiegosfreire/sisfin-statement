package br.com.dfdevforge.sisfinstatement.services.statement;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.BaseService;
import br.com.dfdevforge.sisfinstatement.entities.StatementEntity;
import br.com.dfdevforge.sisfinstatement.entities.UserEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.UserUnauthorizedException;
import br.com.dfdevforge.sisfinstatement.feignclients.UserFeignClient;
import feign.FeignException;

public abstract class StatementBaseService extends BaseService {
	protected StatementEntity statementParam;

	@Autowired private UserFeignClient userFeignClient;

	public void setParams(StatementEntity statementEntity, String token) {
		this.statementParam = statementEntity;
		this.token = token;
	}

	@Override
	public void validateUserAccess() throws BaseException {
		UserEntity userValidatedByToken = null;

		try {
			userValidatedByToken = this.userFeignClient.validateToken(this.token);

			if (this.statementParam.getUserIdentity() != userValidatedByToken.getIdentity())
				throw new UserUnauthorizedException();
		}
		catch (FeignException e) {
			throw new UserUnauthorizedException();
		}
	}
}