package com.example.loginapp.ui.home

import androidx.compose.runtime.Composable
import com.example.loginapp.ui.home.leaves.*
import com.kijlee.android.demo.ui.compose.model.Module

@Composable
fun HomeLeafDispatcher(module: Module, onBack: () -> Unit) {
    when (module.name) {
        "实物资产" -> PhysicalAssetsScreen(onBack)
        "财会" -> AccountingScreen(onBack)
        "车务" -> VehicleMgmtScreen(onBack)
        "文件与资料" -> DocumentsInfoScreen(onBack)
        "订单" -> OrderScreen(onBack)
        "投诉" -> ComplaintScreen(onBack)
        "商品" -> GoodsScreen(onBack)
        "销售统计" -> SalesStatsScreen(onBack)
        "登录记录" -> LoginRecordScreen(onBack)
        "职工档案" -> StaffArchivesScreen(onBack)
        "考勤" -> AttendanceScreen(onBack)
        "请假动态" -> LeaveStatusScreen(onBack)
        "加班动态" -> OvertimeStatusScreen(onBack)
        "工资" -> SalaryScreen(onBack)
        "工作记录" -> WorkRecordScreen(onBack)
        "近7日打卡记录" -> RecentClockInScreen(onBack)
        "客户信息" -> ClientInfoScreen(onBack)
        "订购信息" -> OrderingInfoScreen(onBack)
        "账务信息" -> FinancialInfoScreen(onBack)
        "资金" -> FundsScreen(onBack)
        "票据" -> BillsScreen(onBack)
        "审批权限" -> ApprovalPermScreen(onBack)
        "原辅材料库" -> RawMaterialRepoScreen(onBack)
        "成品库" -> FinishedProductRepoScreen(onBack)
        else -> {
            // Fallback for unknown modules
            androidx.compose.material3.Text(text = "Unknown Module: ${module.name}")
        }
    }
}
