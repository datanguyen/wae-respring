
import React from "react"
import { connect } from "react-redux"

import { CateBarView } from "./catebar.view"

@connect(state => ({catebar: state.catebar}))
export class CateBarComponent extends React.Component {
    render() {
        return (
          <CateBarView catebar={this.props.catebar}/>
        )
    }
}