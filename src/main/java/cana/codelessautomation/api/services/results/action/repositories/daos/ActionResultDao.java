package cana.codelessautomation.api.services.results.action.repositories.daos;

import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.results.action.repositories.daos.enums.ActionResultStatusDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "action_result")
public class ActionResultDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private Long actionId;
    @JMap
    @Column(name = "testcase_result_id")
    private Long testcaseResultId;
    @JMap
    private OffsetDateTime startedOn;
    @JMap
    private OffsetDateTime completedOn;
    @JMap
    @Column(name = "error_message")
    private String errorMessage;
    @JMap
    @Column(name = "execution_order")
    private Long executionOrder;
    @JMap
    @Enumerated(EnumType.STRING)
    private ActionResultStatusDao status;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @OneToOne
    @JoinColumn(name = "actionId", referencedColumnName = "id", updatable = false, insertable = false)
    private ActionDao action;
    @JMap
    private String duration;
}
