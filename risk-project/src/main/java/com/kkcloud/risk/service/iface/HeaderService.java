package com.kkcloud.risk.service.iface;

import com.kkcloud.risk.dto.HeaderDTO;
import com.kkcloud.risk.model.Header;
import com.kkcloud.risk.model.PermissionHeader;
import java.util.List;

public interface HeaderService {
  List<PermissionHeader> getHeader(HeaderDTO headerDTO);

  abstract void savePermissionHead(HeaderDTO headerDTO);

  List<Header> getAddHeader(HeaderDTO headerDTO);
}
