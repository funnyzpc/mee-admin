package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlock;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

/**
 * SysShedlockService
 *
 * @author shaoow
 * @version 1.0
 * @className SysShedlockService
 * @date 2023/9/27 13:56
 */
public interface SysShedlockService {

    MeeResult<Page<SysShedlock>> list(String name, String label, LocalDateTime locked_at_start, LocalDateTime locked_at_end, int pageIdx, int pageSize);
    MeeResult<Integer> update(SysShedlock sysShedlock);
    MeeResult<Integer> delete(@RequestParam(required = true) String name);

}
