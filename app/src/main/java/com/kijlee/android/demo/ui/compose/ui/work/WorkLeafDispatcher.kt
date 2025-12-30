package com.kijlee.android.demo.ui.compose.ui.work

import androidx.compose.runtime.Composable
import com.kijlee.android.demo.ui.compose.model.Module
import com.kijlee.android.demo.ui.compose.ui.work.leaves.*

@Composable
fun WorkLeafDispatcher(module: Module, onBack: () -> Unit) {
    when (module.name) {
        "公司总览" -> CompanyOverviewScreen(onBack)
        "启用管理" -> EnableMgmtScreen(onBack)
        "草稿编辑器" -> DraftEditorScreen(onBack)
        "工作记录点评" -> WorkRecordReviewScreen(onBack)
        "中枢管控" -> CentralControlScreen(onBack)
        "冻结账号" -> FreezeAccountScreen(onBack)
        "日常事务" -> DailyAffairsScreen(onBack)
        "跨机构访问" -> CrossOrgAccessScreen(onBack)
        else -> {
            // Fallback for unknown modules or folders handled elsewhere
            androidx.compose.material3.Text(text = "Unknown Work Module: ${module.name}")
        }
    }
}
