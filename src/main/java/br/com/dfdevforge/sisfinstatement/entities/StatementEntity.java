package br.com.dfdevforge.sisfinstatement.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.dfdevforge.common.entities.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = {"identity"})
@Entity
@Table(name = "sta_statement")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "identity")
public class StatementEntity extends BaseEntity {
	@Id
	@Column(name = "sta_identity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identity;

	@Column(name = "sta_year")
	private Integer year;

	@Column(name = "sta_month")
	private Integer month;

	@Column(name = "sta_opening_balance")
	private BigDecimal openingBalance;

	@Column(name = "sta_closing_balance")
	private BigDecimal closingBalance;

	@Column(name = "sta_is_closed")
	private Boolean isClosed;

	@ManyToOne
	@JoinColumn(name = "stt_identity")
	private StatementTypeEntity statementType;

	@Column(name = "usr_identity")
	private Long userIdentity;
}