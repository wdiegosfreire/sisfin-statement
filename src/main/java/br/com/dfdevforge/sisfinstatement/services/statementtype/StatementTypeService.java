package br.com.dfdevforge.sisfinstatement.services.statementtype;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dfdevforge.common.exceptions.BaseException;
import br.com.dfdevforge.common.services.BaseService;
import br.com.dfdevforge.sisfinstatement.entities.StatementTypeEntity;
import br.com.dfdevforge.sisfinstatement.entities.UserEntity;
import br.com.dfdevforge.sisfinstatement.exceptions.UserUnauthorizedException;
import br.com.dfdevforge.sisfinstatement.feignclients.UserFeignClient;
import feign.FeignException;

public abstract class StatementTypeService extends BaseService {
	protected StatementTypeEntity statementTypeParam;

	@Autowired private UserFeignClient userFeignClient;

	public void setParams(StatementTypeEntity statementTypeEntity, String token) {
		this.statementTypeParam = statementTypeEntity;
		this.token = token;
	}

	@Override
	public void validateUserAccess() throws BaseException {
		UserEntity userValidatedByToken = null;

		try {
			userValidatedByToken = this.userFeignClient.validateToken(this.token);

			if (this.statementTypeParam.getUserIdentity() != userValidatedByToken.getIdentity())
				throw new UserUnauthorizedException();
		}
		catch (FeignException e) {
			throw new UserUnauthorizedException();
		}
	}
}