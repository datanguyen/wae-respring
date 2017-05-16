import React from 'react'
import { connect } from 'react-redux'
import { ElectronicView } from './electronic.view'

@connect(state => ({ electronicApi: state.electronic }))
export class Electronic extends React.Component {
  render() {
    return(
      <ElectronicView  electronicApi={this.props.electronicApi}/>
    )
  }
}
