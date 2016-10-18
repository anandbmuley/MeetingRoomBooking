package com.merobo.utils

import com.merobo.beans.TeamBean
import com.merobo.builders.TeamBeanBuilder
import com.merobo.common.AssertionsCatalogue
import com.merobo.dtos.TeamTo
import org.testng.annotations.Test

class DtoCreatorUtilTest {

    @Test
    public void ShouldCreateTeamTo() {
        // GIVEN
        TeamBean teamBean = new TeamBeanBuilder().build()

        // WHEN
        TeamTo actual = DtoCreatorUtil.createTeamTo(teamBean)

        // THEN
        AssertionsCatalogue.assertTeamTo(actual)
    }

}
