import React from 'react'
import { connect } from 'react-redux'
import { HandmadeView } from './handmade.view'

@connect(state => ({ handmadeApi: state.handmade }))
export class Handmade extends React.Component {
  render() {
    return(
      <HandmadeView  handmadeApi={this.props.handmadeApi} />
    )
  }
}
