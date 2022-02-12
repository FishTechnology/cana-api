package cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "action_option_result")
@Data
public class ActionOptionResultDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "action_result_id", nullable = false)
    @Column(name = "action_result_id")
    private Long actionResultId;

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "action_option_id", nullable = false)
    @Column(name = "action_option_id")
    private Long actionOptionId;

    @Column(name = "startedon")
    private OffsetDateTime startedOn;

    @Column(name = "completedon")
    private OffsetDateTime completedOn;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionOptionResultStatus status;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "createdon")
    private OffsetDateTime createdOn;

    @Column(name = "modifiedon")
    private OffsetDateTime modifiedOn;

    @Column(name = "execution_order")
    private Long executionOrder;

    @Column(name = "duration")
    private Long duration;
}